function SDO()
    fid = fopen('Paradeigmata.txt', 'w');
    rand('seed', now);

    % 1) 100 points in the square [-2.0, -1.6] x [1.6, 2.0]
    for i = 1:100
        x1 = createRandomNums(-2.0, -1.6);
        x2 = createRandomNums(1.6, 2.0);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 2) 100 points in the square [-1.2, -0.8] x [1.6, 2.0]
    for i = 1:100
        x1 = createRandomNums(-1.2, -0.8);
        x2 = createRandomNums(1.6, 2.0);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 3) 100 points in the square [-0.4, 0] x [1.6, 2.0]
    for i = 1:100
        x1 = createRandomNums(-0.4, 0);
        x2 = createRandomNums(1.6, 2.0);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 4) 100 points in the square [-1.8, -1.4] x [0.8, 1.2]
    for i = 1:100
        x1 = createRandomNums(-1.8, -1.4);
        x2 = createRandomNums(0.8, 1.2);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 5) 100 points in the square [-0.6, -0.2] x [0.8, 1.2]
    for i = 1:100
        x1 = createRandomNums(-0.6, -0.2);
        x2 = createRandomNums(0.8, 1.2);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 6) 100 points in the square [-2.0, -1.6] x [0, 0.4]
    for i = 1:100
        x1 = createRandomNums(-2.0, -1.6);
        x2 = createRandomNums(0, 0.4);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 7) 100 points in the square [-1.2, -0.8] x [0, 0.4]
    for i = 1:100
        x1 = createRandomNums(-1.2, -0.8);
        x2 = createRandomNums(0, 0.4);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 8) 100 points in the square [-0.4, 0] x [0, 0.4]
    for i = 1:100
        x1 = createRandomNums(-0.4, 0);
        x2 = createRandomNums(0, 0.4);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end

    % 9) 200 points in the square [-2.0, 0] x [0, 2.0]
    for i = 1:200
        x1 = createRandomNums(-2.0, 0);
        x2 = createRandomNums(0, 2.0);
        fprintf(fid, '%.4f\t%.4f\n', x1, x2);
    end
    fclose(fid);
end

function r = createRandomNums(min_val, max_val)
    r = min_val + (max_val - min_val) * rand();
end

SDO();
