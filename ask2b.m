data = load('Paradeigmata.txt');
centers = load('Centers.txt');

figure;
plot(data(:, 1), data(:, 2), '+b', 'DisplayName', 'Data Points');
hold on;

plot(centers(:, 1), centers(:, 2), '*r', 'MarkerSize', 12, 'LineWidth', 2, 'DisplayName', 'Centers');

title('K-Means Results for M=4');
xlabel('x1');
ylabel('x2');
grid on;

hold off;

